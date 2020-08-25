package br.com.jardel.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

        if(!StringUtils.isNumeric(numberOne) || !StringUtils.isNumeric(numberTwo)) {
            throw  new UnsupportedOperationException("Please set a valid numeric!");
        }

        return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

        if(!StringUtils.isNumeric(numberOne) || !StringUtils.isNumeric(numberTwo)) {
            throw  new UnsupportedOperationException("Please set a valid numeric!");
        }

        return Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);
    }

    @GetMapping("/mult/{numberOne}/{numberTwo}")
    public Double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

        if(!StringUtils.isNumeric(numberOne) || !StringUtils.isNumeric(numberTwo)) {
            throw  new UnsupportedOperationException("Please set a valid numeric!");
        }

        return Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

        if(!StringUtils.isNumeric(numberOne) || !StringUtils.isNumeric(numberTwo)) {
            throw  new UnsupportedOperationException("Please set a valid numeric!");
        }

        Double number1 = Double.parseDouble(numberOne);
        Double number2 = Double.parseDouble(numberTwo);

        if(number2 == 0) {
            throw  new UnsupportedOperationException("Please set a numberTwo diferent 0!");
        }

        return number1 / number2;
    }

    @GetMapping("/mid/{numberOne}/{numberTwo}")
    public Double mid(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

        return sum(numberOne, numberTwo) / 2;
    }

    @GetMapping("/raiz/{numberOne}")
    public Double raiz(@PathVariable("numberOne") String numberOne) {

        if(!StringUtils.isNumeric(numberOne)) {
            throw  new UnsupportedOperationException("Please set a valid numeric!");
        }

        Double number1 = Double.parseDouble(numberOne);

        if(number1 == 0) {
            throw  new UnsupportedOperationException("Please set a numberTwo diferent 0!");
        }

        return Math.sqrt(number1);
    }
}
