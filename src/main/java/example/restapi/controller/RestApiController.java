package example.restapi.controller;

import example.restapi.model.RegistResponse;
import example.restapi.model.SearchResponse;
import example.restapi.model.SearchParam;
import example.restapi.model.Student;
import example.restapi.type.SubjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class RestApiController {

    Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @GetMapping(path={"/student", "/student/{id}"}, produces = "application/json;charset=UTF-8")
    public SearchResponse get(@Valid @ModelAttribute SearchParam searchParam, @PathVariable(required = false) Integer id, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            return new SearchResponse(bindingResult);
        }
        Student student = new Student();
        student.setId(1);
        student.setName("山田花子");
        student.setNameKana("ヤマダハナコ");
        student.setGrade(1);
        student.setSubject(SubjectType.経営学部);
        logger.info(searchParam.toString());
        SearchResponse response = new SearchResponse();
        response.setResultCode("OK");
        response.setStudentList(List.of(student));
        return response;
    }



    @PostMapping(path="/student", produces = "application/json;charset=UTF-8")
    public RegistResponse post(@RequestBody @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RegistResponse(bindingResult);
        }
        RegistResponse response = new RegistResponse();
        response.setResultCode("OK");
        return response;
    }

}
