package topic49_design_patterns;

/*
 * Pattern  : Builder - create an object that has many parts (mostly optional), in a readable way
 * Use for  : objects with lots of fields, where most of them are optional.
 * Key idea : Instead of new Pizza("large", true, false, true, 2) - which value means what?? -
 *            you chain named steps and finish with build(). The final object can still be immutable.
 *            Like ordering a Subway sandwich: bread, then cheese, then veggies, then sauce -
 *            one clear step at a time, and you skip whatever you don't want.
 * Spring   : RestClient.builder(), UriComponentsBuilder, and Lombok's @Builder (stage 05).
 * Run      : java -cp out topic49_design_patterns.BuilderPattern
 */
public class BuilderPattern {

    static final class HttpRequest {
        private final String url;
        private final String method;
        private final int timeoutSeconds;
        private final String body;

        private HttpRequest(Builder builder) {      // private: only the builder can create one
            this.url = builder.url;
            this.method = builder.method;
            this.timeoutSeconds = builder.timeoutSeconds;
            this.body = builder.body;
        }

        static Builder to(String url) {             // the one part that is required - so you must give it first
            return new Builder(url);
        }

        @Override
        public String toString() {
            return method + " " + url + " (timeout " + timeoutSeconds + "s" + (body == null ? "" : ", body " + body) + ")";
        }

        static final class Builder {
            private final String url;
            private String method = "GET";          // sensible default values for the optional parts
            private int timeoutSeconds = 30;
            private String body;

            private Builder(String url) {
                this.url = url;
            }

            Builder method(String method) {
                this.method = method;
                return this;                        // returning 'this' is what lets you chain .method(...).body(...)
            }

            Builder timeout(int seconds) {
                this.timeoutSeconds = seconds;
                return this;
            }

            Builder body(String body) {
                this.body = body;
                return this;
            }

            HttpRequest build() {
                if (body != null && method.equals("GET")) {
                    throw new IllegalStateException("a GET request has no body");   // check everything once, at the very end
                }
                return new HttpRequest(this);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(HttpRequest.to("/products").build());                                            // only the required part
        System.out.println(HttpRequest.to("/orders").method("POST").body("{\"id\":1}").timeout(5).build());   // everything set
    }
}
