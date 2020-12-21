package lab.model.dao.entities;

public class Account {
    private int user_id;
    private double score;

    Account(AccountBuilder accountBuilder) {
        this.user_id = accountBuilder.user_id;
        this.score = accountBuilder.score;
    }

    Account() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static class AccountBuilder {
        private int user_id;
        private double score;

        public AccountBuilder setUser_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public AccountBuilder setScore(double score) {
            this.score = score;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
