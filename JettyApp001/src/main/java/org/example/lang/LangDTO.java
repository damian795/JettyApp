package org.example.lang;

public class LangDTO {
    private Integer id;
    private String code;

    public LangDTO(Lang lang) {
        this.code = lang.getCode();
        this.id = lang.getId();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
