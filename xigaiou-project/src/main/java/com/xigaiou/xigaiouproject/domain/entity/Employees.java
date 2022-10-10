package com.xigaiou.xigaiouproject.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@TableName("employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @TableId
    private String employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date hireDate;

    private String jobId;

    private String salary;

    private String commissionPct;

    private String managerId;

    private String departmentId;
}
