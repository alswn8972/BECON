<template>
  <div>
    <Nav />
    <PageHeader title="콘서트 상세" />
    <ConcertDetail />
    <Footer />
  </div>
</template>
<script>
import Nav from "../components/layout/Nav";
import PageHeader from "../components/layout/PageHeader";
import Footer from "../components/layout/Footer";
import ConcertDetail from "../components/concert/ConcertDetail";

export default {
  components: {
    ConcertDetail,
    Footer,
    PageHeader,
    Nav,
  },
  head() {
    return {
      title: "BeCon | Concert Detail",
    };
  },
};
</script>
