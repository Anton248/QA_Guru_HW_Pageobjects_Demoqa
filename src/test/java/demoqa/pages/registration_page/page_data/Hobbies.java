package demoqa.pages.registration_page.page_data;

public enum Hobbies {

    SPORTS ("Sports"),
    READING("Reading"),
    MUSIC("Music");


    final String title;

    Hobbies(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
