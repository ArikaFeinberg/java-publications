package lab.model.dao.entities;

import lab.model.dao.entities.enums.Theme;

public class Publication {
    private int publication_id;
    private Theme theme;
    private String name;
    private double price;
    
    Publication(PublicationBuilder publicationBuilder){
        this.publication_id = publicationBuilder.publication_id;
        this.name = publicationBuilder.name;
        this.theme = publicationBuilder.theme;
        this.price = publicationBuilder.price;

    }
    Publication(){

    }
    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static class PublicationBuilder{
        private int publication_id;
        private Theme theme;
        private String name;
        private double price;

        public PublicationBuilder setPublication_id(int publication_id) {
            this.publication_id = publication_id;
            return this;
        }

        public PublicationBuilder setTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public PublicationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PublicationBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Publication build(){
            return new Publication(this);
        }
    }
}
