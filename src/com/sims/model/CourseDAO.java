/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class CourseDAO {

    PreparedStatement pst;
    ResultSet rs;

    public boolean saveCourse(Course course) {
        boolean value = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("insert into course_module values(?,?,?,?,?,?);");
            pst.setString(1, course.getCourseid());
            pst.setString(2, course.getCourseName());
            pst.setInt(3, course.getCredit());
            pst.setString(4, course.getCourse_dpt());
            pst.setInt(5, course.getCourseyear());
            pst.setInt(6, course.getCourseSemester());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean updateCourse(Course course) {
        boolean value = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("update course_module set course_name=?,credit=?,course_dpt=?,course_year=?,course_semester=? where courseid=?;");
            pst.setString(6, course.getCourseid());
            pst.setString(1, course.getCourseName());
            pst.setInt(2, course.getCredit());
            pst.setString(3, course.getCourse_dpt());
            pst.setInt(4, course.getCourseyear());
            pst.setInt(5, course.getCourseSemester());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean deleteCourse(Course course) {
        boolean value = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from course_module where courseid=?;");
            pst.setString(1, course.getCourseid());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public ArrayList<Course> getStudentAllCourseList(String studenid, int year, int sem) {
        ArrayList<Course> courselist = new ArrayList<Course>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select courseid,course_name,credit,course_dpt,course_year,course_semester from course_module,student_course where courseid=sc_courseid and sc_studentid=? and course_year=? and course_semester=?;");
            pst.setString(1, studenid);
            pst.setInt(2, year);
            pst.setInt(3, sem);

            ResultSet resultSet = pst.executeQuery();

            System.out.println("in getStudentAllCourseList()");

            Course c1;
            while (resultSet.next()) {
                c1 = new Course();

                c1.setCourseid(resultSet.getString(1));
                c1.setCourseName(resultSet.getString(2));
                c1.setCredit(resultSet.getInt(3));
                c1.setCourse_dpt(resultSet.getString(4));
                c1.setCourseyear(resultSet.getInt(5));
                c1.setCourseSemester(resultSet.getInt(6));
                c1.setGrade(getCoursegrade(resultSet.getString(1), studenid));

                System.out.println(c1.getCourseid());

                courselist.add(c1);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courselist;
    }

    public ArrayList<Course> getLveltAllCourseList(int year, int sem) {
        ArrayList<Course> courselist = new ArrayList<Course>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from course_module where course_year=? and course_semester=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, year);
            pst.setInt(2, sem);

            rs = pst.executeQuery();

//            System.out.println("in ");
            while (rs.next()) {
                Course c1 = new Course();

                c1.setCourseid(rs.getString(1));
                c1.setCourseName(rs.getString(2));
                c1.setCredit(rs.getInt(3));
                c1.setCourse_dpt(rs.getString(4));
                c1.setCourseyear(rs.getInt(5));
                c1.setCourseSemester(rs.getInt(6));

//                System.out.println(c1.getCourseid());
                courselist.add(c1);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courselist;
    }

    public String getCoursegrade(String courseid, String studentid) {
        int gradeDetails[] = getCoursegradingDetails(courseid);
        float mark = 0;
        String grade;

        if (gradeDetails[0] > 0) {
            mark += getCAmaks(courseid, studentid, "Q") * gradeDetails[0];
        }

        if (gradeDetails[1] > 0) {
            mark += getCAmaks(courseid, studentid, "A") * gradeDetails[1];
        }

        if (gradeDetails[2] > 0) {
            mark += getCAmaks(courseid, studentid, "M") * gradeDetails[2];
        }

        if (gradeDetails[3] > 0) {
            mark += getCAmaks(courseid, studentid, "T") * gradeDetails[3];
        }

        if (gradeDetails[4] > 0) {
            mark += getCAmaks(courseid, studentid, "P") * gradeDetails[4];
        }

        if (mark >= 85) {
            grade = "A+";
        } else if (mark >= 75) {
            grade = "A";
        } else if (mark >= 70) {
            grade = "A-";
        } else if (mark >= 65) {
            grade = "B+";
        } else if (mark >= 60) {
            grade = "B";
        } else if (mark >= 55) {
            grade = "B-";
        } else if (mark >= 50) {
            grade = "C+";
        } else if (mark >= 45) {
            grade = "C";
        } else if (mark >= 40) {
            grade = "C-";
        } else if (mark >= 35) {
            grade = "D+";
        } else if (mark >= 30) {
            grade = "D";
        } else {
            grade = "E";
        }

        return grade;
    }

    public ArrayList<Grade> getAllCoursegrade(String courseid) {
        ArrayList<String> studentlist = new ArrayList<String>();
        ArrayList<Grade> grades = new ArrayList<Grade>();

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select sc_studentid from course_module,student_course where courseid=sc_courseid and courseid=?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseid);

            rs = pst.executeQuery();

            while (rs.next()) {
                studentlist.add(rs.getString(1));
            }

            for (String student : studentlist) {
                Grade grade = new Grade();

                grade.setCourseid(courseid);
                grade.setStudentid(student);
                grade.setGrade(getCoursegrade(courseid, student));

                grades.add(grade);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return grades;
    }

    public ArrayList<Grade> getStudentCoursegrade(String studenid, int year, int sem) {
        ArrayList<Course> courselist = getStudentAllCourseList(studenid,year,sem);
        ArrayList<Grade> grades = new ArrayList<Grade>();

        for (Course course : courselist) {
            Grade grade = new Grade();

            grade.setCourseid(course.getCourseid());
            grade.setStudentid(studenid);
            grade.setGrade(getCoursegrade(course.getCourseid(), studenid));

            grades.add(grade);
        }

        return grades;
    }

    public float getCAmaks(String courseid, String stdid, String examtype) {
        float mark = 0, min = 100, sum = 0;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            String sql = "select mark from exam,marks where examid=marks_examid and exam_courseid=? and marks_st_id=? and exam_type like '" + examtype + "%';";
//            System.out.println(sql);

            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseid);
            pst.setString(2, stdid);

            rs = pst.executeQuery();
            int size = 0;

            if (rs.first()) {

                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            }

            switch (size) {
                case 1:
                    rs.first();
                    mark = rs.getFloat(1) / 100;
                    break;

                case 3:
                    while (rs.next()) {
                        if (rs.getFloat(1) < min) {
                            min = rs.getFloat(1);
                        }
                        sum += rs.getFloat(1);
                    }
//                    System.out.println("sum " + sum + " min " + min);
                    mark = (sum - min) / 20;
                    break;
                case 4:
                    while (rs.next()) {
                        if (rs.getFloat(1) < min) {
                            min = rs.getFloat(1);
                        }
                        sum += rs.getFloat(1);
                    }
                    System.out.println("sum " + sum + " min " + min);
                    mark = (sum - min) / 30;
                    break;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(mark);
        return mark;
    }

    public int[] getCoursegradingDetails(String courseid) {
        int arr[] = new int[5];
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from courese_gradding_details where grade_courseid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseid);

            rs = pst.executeQuery();

            if (rs.first()) {

                arr[0] = rs.getInt(2);
                arr[1] = rs.getInt(3);
                arr[2] = rs.getInt(4);
                arr[3] = rs.getInt(5);
                arr[4] = rs.getInt(6);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;
    }

}
