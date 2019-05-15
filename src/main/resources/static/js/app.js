new Vue({
    el: '#app',

    data: {
        tweets: [],
        newTweet: {}
    },
    mounted: function () {
        this.loadTweets();
    },
    methods: {
        getCsrfHeaders() {
            const header = $("meta[name='_csrf_header']").attr("content");
            const token = $("meta[name='_csrf']").attr("content");
            let headers = {};
            headers[header] = token;
            return headers;
        },

        loadTweets() {
            let self = this;
            $.getJSON( "api/tweets", function( data ) {
                self.tweets = data
            });
        },

        saveTweet() {
            let self = this;

            $.ajax({
                type: "POST",
                url: 'api/tweets',
                headers: self.getCsrfHeaders(),
                data: JSON.stringify(this.newTweet),
                contentType: "application/json",
                success: function() {
                    self.newTweet = {};
                    self.loadTweets();
                }
            });
        },

        deleteTweet(id) {
            let self = this;

            $.ajax({
                type: "DELETE",
                url: 'api/tweets/'+id,
                headers: self.getCsrfHeaders(),
                success: function() {
                    self.loadTweets();
                }
            });
        }
    },
    computed: {

    }
});