package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello_mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello_template";
    }

    /**
     * ResponseBody: 응답부(response)에 바로 값을 넣어주겠다는 뜻
     * */
    @GetMapping("hello_string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello "+name; //view 거치지 않고 문자 그대로 송출
    }
    @GetMapping("hello_api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name, Model model) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 반환
    }
    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}