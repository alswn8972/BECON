package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {
    private static final long serialVersionUID = 846542477L;
    public static final QUser user = new QUser("user");
    public final QBaseEntity _super = new QBaseEntity(this);
    //inherited
    public final NumberPath<Long> id = _super.id;
    public final StringPath userId = createString("userId");
    public final StringPath password = createString("password");
    public final StringPath name = createString("name");
    public final StringPath phone = createString("phone");
    public final StringPath email = createString("email");
    public final NumberPath<Long> userProfileId = createNumber("userProfileId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

