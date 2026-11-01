package calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }

    @GetMapping("/calc")
    public String calc(
            @RequestParam double a,
            @RequestParam String op,
            @RequestParam double b
    ){
        double res;
        switch (op){
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                if(b == 0){
                    return "错误：除数不能为0";
                }
                res = a / b;
                break;
            default:
                return "错误：运算符只支持 + - * /";
        }
        return String.valueOf(res);
    }
}
