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
                success: function() {
                    self.loadTweets();
                }
            });
        }
    },
    computed: {

    }
});