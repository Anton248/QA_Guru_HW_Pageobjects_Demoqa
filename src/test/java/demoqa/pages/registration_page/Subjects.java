package demoqa.pages.registration_page;

public enum Subjects {

    HINDI("Hindi"),
    ENGLISH("English"),
    MATHS("Maths"),
    PHYSICS("Physics"),
    BIOLOGY("Biology"),
    COMMERCE("Commerce"),
    ACCOUNTING("Accounting"),
    ECONOMICS("Economics"),
    ARTS("Arts"),
    SOCIALSTUDIES("Social Studies"),
    HISTORY("History"),
    CIVICS("Civics");

    final String title;

    Subjects(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
