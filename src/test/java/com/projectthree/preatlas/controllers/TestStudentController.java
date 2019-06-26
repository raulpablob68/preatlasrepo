package com.projectthree.preatlas.controllers;

import com.projectthree.preatlas.beans.Student;
import com.projectthree.preatlas.repository.IStudentDao;
import com.projectthree.preatlas.services.IStudentService;
import com.projectthree.preatlas.services.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class TestStudentController {

    public String sDate1 = "1998/06/21";
    public String sDate2 = "21/06/1998";
    public String sDate3 = "1997-03-15T00:00:01Z";
    public  SimpleDateFormat asdf = new SimpleDateFormat("yyyy/MM/dd");
    public  SimpleDateFormat asdf2 = new SimpleDateFormat("dd/MM/yyyy");
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    public  Date date1;
    public Date formatter()
//            throws Exception
    {
        try {
//            date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        date1 = asdf2.parse(sDate2);
        asdf2.format(date1);
//            date1.
        System.out.println("date1: " + date1);
            return  date1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }

    public Date formatter2(String dateString) throws Exception{
        Date dateBase = simpleDateFormat.parse(dateString.replaceAll("Z$","+0000"));
        System.out.println("dateBase: " + dateBase);
        return dateBase;
    }

    public Date formatter3(String dateString2) throws ParseException{
        Date dateBase2 = simpleDateFormat.parse(dateString2.replaceAll("Z$","+0000"));
        System.out.println("dateBase2: " + dateBase2);
        String dX = simpleDateFormat.format(dateBase2);
        System.out.println("dX: " + dX);
        return simpleDateFormat.parse(dX);
    }

    Student studentMock =
            new Student(
                    1,
                    'M',
                    "Jose",
                    "Luis",
                    "Perez",
                    date1,
                    (byte)1);

    List<Student> studentListMock = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    //private StudentServiceImpl studentService;
    private IStudentService studentService;

    @Test
    public void givenAListOfStudents_whenGetAllStudents_thenCorrect() throws Exception {
        Date d1;
//        d1 = new TestStudentController().formatter();
        d1 = formatter3(sDate3);
        System.out.println("d1: "+ d1);
        studentMock.setStudentDateOfBirth(d1);
        System.out.println("setStudentDateOfBirth: " + studentMock.getStudentDateOfBirth());
        studentListMock.add(studentMock);
//        Mockito.when(studentService.getAllStudents()).thenReturn(studentListMock);
        Mockito.when(studentService.getAllStudents()).thenReturn(studentListMock);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/1.0/students/").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

//        String expected = "[{studentId:1,studentGender:M,studentFirstName:Jose,studentMiddleName:Luis,studentLastName:Perez,studentDateOfBirth:1998-06-21,studentStatus:1}]";
        String expected = "[{studentId:1,studentGender:M,studentFirstName:Jose,studentMiddleName:Luis,studentLastName:Perez,studentStatus:1}]";

        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);

    }

    @Test
    public void testGetOneStudentrue() throws Exception {
        Mockito.when(studentService.getOneStudent(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(studentMock));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/1.0/students/1/")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("result.getResponse().toString(): " + result.getResponse().getContentAsString());
        String expected = "{studentId:1,studentGender:M,studentFirstName:Jose,studentMiddleName:Luis,studentLastName:Perez,studentStatus:1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }


}
