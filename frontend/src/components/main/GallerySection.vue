<template>
  <section class="gallery-section">
    <div class="auto-container">
      <!--MixitUp Galery-->
      <div class="mixitup-gallery">
        <div class="upper-row clearfix">
          <div class="sec-title">
            <h2>공연 목록 <span class="dot">.</span></h2>
          </div>
          <!--Filter-->
          <div class="filters clearfix">
            <ul class="filter-tabs filter-btns clearfix">
              <li class="filter mixitup-control-active" data-role="button" data-filter="all" @click="click('All')">All<sup></sup></li>
              <span v-for="category in getCategories" :key="category.id">
                <li class="filter" data-role="button" :data-filter="category.id" @click="click(category.name)">
                  {{ category.name }}<sup></sup>
                </li>
              </span>
            </ul>
          </div>
        </div>

        <div class="filter-list row">
          <div class="sec-title centered" v-if ="getConcertInfos.length == 0">
            <img src="@/common/images/sleepy.png" width="200" height="200">
            <div style="font-size: 36px; color: black;">예정된 공연이 없습니다. </div>
          </div>
          <div class="row clearfix" v-if ="getConcertInfos.length != 0">
            <no-ssr>
              <carousel :perPage="4">
                <slide v-for="concertInfo in getConcertInfos"
                  :key="concertInfo.id" v-model="concertInfo.id">
                <!-- Gallery Item -->
                <div class="gallery-item mx-3">
                  <div class="inner-box" @click="goDetail(concertInfo)">
                    <div class="image-box">
                    <figure class="image">
                      <img
                        :src="`https://i5d102.p.ssafy.io/posterImg/${concertInfo.poster.originName}`"
                        alt=""
                      />
                    </figure>
                    </div>
                    <a
                      class="lightbox-image overlay-box"
                      data-fancybox="gallery"
                    ></a>
                    <div class="cap-box">
                      <div class="cap-inner">
                        <div class="cat">
                          <span>{{ concertInfo.title }}</span>
                        </div>
                        <div class="title">
                          <h5>
                            <a>{{ concertInfo.description | truncate(20) }}</a>
                          </h5>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                </slide>
              </carousel>
            </no-ssr>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import NoSsr from "vue-no-ssr";
import { Carousel, Slide } from "vue-carousel";
import GLightbox from "glightbox";
import mixitup from "mixitup";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "GallerySection",

  components: {
    NoSsr,
    Carousel,
    Slide,
  },

  data() {
    return {
      mixer: null,
      concertInfo: {
        id: '',
      },
    };
  },

  mounted() {
    const containerEl = document.querySelector(".filter-list");
    this.mixer = new mixitup(containerEl, {});
    new GLightbox({
      selector: ".lightbox-image",
      touchNavigation: true,
      loop: true,
      autoplayVideos: true,
    });
  },

  created() {
    this.requestCategory();
    this.requestConcert("All");
  },

  computed: {
    ...mapGetters("concert", ["getCategories", "getConcertInfos"]),
  },

  methods: {
    ...mapActions("concert", ["requestCategory", "requestConcert", "findConcertDetail"]),
    click(data) {
      this.requestConcert(data);
    },
    goDetail(concertInfo) {
      this.findConcertDetail(concertInfo)
      this.$router.push("concertDetail");
    },
  },
};
</script>

<style scoped>
.gallery-item {
  height: 400px;
}
</style>
