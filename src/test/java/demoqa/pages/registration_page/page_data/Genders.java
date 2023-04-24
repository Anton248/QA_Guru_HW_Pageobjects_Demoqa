package demoqa.pages.registration_page.page_data;

public enum Genders {
    MALE ("Male"),
    FEMALE("Female"),
    OTHER("Other");

    final String title;

    Genders(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
