package clem.app.jnews.bean

data class NewsItem(
        /**
         * category : tech_science/list
         * desc :
         * image : ./photo/AS20180220003821.html
         * main_title :
         * subcategory :
         * time : 2018-02-24
         * url : https://www.asahi.com/articles/ASL2B01DPL29PLFA012.html
         */

        var title: String,
        var category: String,
        var desc: String,
        var image: String,
        var main_title: String,
        var subcategory: String,
        var time: String,
        var url: String
)