package com.example.pcs.fragmentcase.bean;

import com.example.pcs.fragmentcase.base.BaseModel;

import java.util.List;

/**
 * @author pcs
 * @since 2018-07-08.
 */
public class MovieDetails extends BaseModel {

    /**
     * rating : {"max":10,"average":3.7,"stars":"20","min":0}
     * reviews_count : 216
     * wish_count : 2912
     * douban_site :
     * year : 2018
     * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523437699.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523437699.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523437699.jpg"}
     * alt : https://movie.douban.com/subject/26818314/
     * id : 26818314
     * mobile_url : https://movie.douban.com/subject/26818314/mobile
     * title : 猛虫过江
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/26818314
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26818314/cinema/
     * episodes_count : null
     * countries : ["中国大陆"]
     * genres : ["喜剧","动作"]
     * collect_count : 10036
     * casts : [{"alt":"https://movie.douban.com/celebrity/1274081/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg"},"name":"小沈阳","id":"1274081"},{"alt":"https://movie.douban.com/celebrity/1316365/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36063.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36063.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36063.jpg"},"name":"潘斌龙","id":"1316365"},{"alt":"https://movie.douban.com/celebrity/1341903/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.jpg"},"name":"宋芸桦","id":"1341903"},{"alt":"https://movie.douban.com/celebrity/1275482/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32611.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32611.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32611.jpg"},"name":"金士杰","id":"1275482"}]
     * current_season : null
     * original_title : 猛虫过江
     * summary : 东北农村青年沈福（小沈阳 饰）与大雷（潘斌龙 饰）遇见南方姑娘静香（宋芸桦 饰），机缘巧合来到陌生大城市，面对从天而降的巨额遗产和随之而来的种种危机，两人接连施展令人意想不到的“独门秘籍”，引发了一连串爆笑喜剧故事。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1274081/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg"},"name":"小沈阳","id":"1274081"}]
     * comments_count : 4760
     * ratings_count : 9766
     * aka : ["The Way of the Bug","A Strong Insect Crossing the River"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<PerformerBean> casts;
    private List<PerformerBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<PerformerBean> getCasts() {
        return casts;
    }

    public void setCasts(List<PerformerBean> casts) {
        this.casts = casts;
    }

    public List<PerformerBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PerformerBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 3.7
         * stars : 20
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523437699.jpg
         * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523437699.jpg
         * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523437699.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }


    public static class PerformerBean {
        /**
         * alt : https://movie.douban.com/celebrity/1274081/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg"}
         * name : 小沈阳
         * id : 1274081
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34055.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
