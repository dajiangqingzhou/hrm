package org.deepsl.hrm.domain;

public class EmployeeCondition {
    private Integer  job_id;
    private String  name;
    private String  cardId;
    private Integer  sex;
    private String phone;
    private Integer  dept_id;

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "EmployeeCondition{" +
                "job_id=" + job_id +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", dept_id=" + dept_id +
                '}';
    }
}
