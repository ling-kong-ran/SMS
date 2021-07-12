package com.ling.stums.dto;

/**
 * @Author Lingkongran
 * @Date 2020/12/2 0002 12:51
 * @Version 1.0
 */
public class ClassScheduleRequestBodyDto {
    private Integer cid;

    public ClassScheduleRequestBodyDto(Integer cid, Integer sid, Integer cno, String teachPlace, String teachTime, String teacher) {

        this.cid = cid;
        this.sid = sid;
        this.cno = cno;
        this.teachPlace = teachPlace;
        this.teachTime = teachTime;
        this.teacher = teacher;
    }

    private Integer sid;
    private Integer cno;
    private String teachPlace;
    private String teachTime;
    private String teacher;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getTeachPlace() {
        return teachPlace;
    }

    public void setTeachPlace(String teachPlace) {
        this.teachPlace = teachPlace;
    }

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public ClassScheduleRequestBodyDto(Integer cid, Integer cno, String teachPlace, String teachTime, String teacher) {
        this.cid = cid;
        this.cno = cno;
        this.teachPlace = teachPlace;
        this.teachTime = teachTime;
        this.teacher = teacher;
    }

    public ClassScheduleRequestBodyDto() {
    }


}
