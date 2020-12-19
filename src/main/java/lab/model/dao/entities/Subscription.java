package lab.model.dao.entities;

public class Subscription {
    private int publication_id;
    private int user_id;
    Subscription(){

    }
    Subscription(SubscriptionBuilder subscriptionBuilder){
        this.publication_id = subscriptionBuilder.publication_id;
        this.user_id = subscriptionBuilder.user_id;

    }
    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public static class SubscriptionBuilder{
        private int publication_id;
        private int user_id;

        public SubscriptionBuilder setPublication_id(int publication_id) {
            this.publication_id = publication_id;
            return this;
        }

        public SubscriptionBuilder setUser_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public Subscription build(){
            return new Subscription(this);
        }
    }
}
