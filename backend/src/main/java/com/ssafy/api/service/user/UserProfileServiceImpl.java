package com.ssafy.api.service.user;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.UserProfilePostReq;
import com.ssafy.common.util.MD5Generator;
import com.ssafy.db.entity.UserProfile;
import com.ssafy.db.repository.UserProfileRepository;
import com.ssafy.db.repository.UserProfileRepositorySupport;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	UserProfileRepository userProfileRepository;
	@Autowired
	UserProfileRepositorySupport userProfileRepositorySupport;
	
	@Transactional
	@Override
	public void changeFile(Long userPID, MultipartFile files) throws NoSuchAlgorithmException, IllegalStateException, IOException {
		UserProfilePostReq userProfileInfo=new UserProfilePostReq();
    	System.out.println(userPID);
	
		String origFilename = files.getOriginalFilename();
        String filename = new MD5Generator(origFilename).toString();
        String savePath = System.getProperty("user.dir") + "\\files";
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + filename;
        files.transferTo(new File(filePath));
        System.out.println(origFilename);
        userProfileInfo.setOriginName(origFilename);
        System.out.println(userProfileInfo.getOriginName());
        userProfileInfo.setName(filename);
        userProfileInfo.setPath(filePath);
    	
        userProfileRepositorySupport.modifyProfile(userPID, userProfileInfo);
	}
	
	
	@Transactional
	@Override
	public UserProfile saveFile(UserProfilePostReq request) {
		return userProfileRepository.save(request.toEntity());
	}

	@Transactional
	@Override
	public UserProfilePostReq getFile(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();

		UserProfilePostReq request = UserProfilePostReq.builder()
				.originName(userProfile.getOriginName())
				.name(userProfile.getName())
				.path(userProfile.getPath())
				.build();
		return request;
	}



}