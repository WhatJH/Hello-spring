package hello.hello_spring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) { //form 전송시 이 함수 이용해서 name값 넘어줌
        this.name = name;
    }
}
