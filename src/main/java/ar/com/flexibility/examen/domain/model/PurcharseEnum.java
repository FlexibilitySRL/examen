package ar.com.flexibility.examen.domain.model;

enum PurcharseEnum {
    APPROVED("APPROVED"),
    REJECTED("REJECTED");
    private String state;


    private PurcharseEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
