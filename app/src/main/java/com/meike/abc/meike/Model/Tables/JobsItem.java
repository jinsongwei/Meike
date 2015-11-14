package com.meike.abc.meike.Model.Tables;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshalling;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.CompanyInfo;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Util.Converters;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = TableName.Jobs)
public class JobsItem extends PostItem {

    private static final String JOB_TITLE = "职位";
    private static final String INDUSTRY = "行业";
    private static final String POSITION_TYPE = "性质";
    private static final String DEGREE = "学历";
    private static final String MAJOR = "专业";
    private static final String EXPERIENCE = "经验";
    private static final String SALARY = "薪水";
    private static final String SKILLS = "技能";
    private static final String REQUIREMENTS = "要求";
    private static final String RESPONSIBILITIES = "任务";
    private static final String BENEFITS = "福利";
    private static final String COMPANY_INFO = "公司";

    private String jobTitle;
    private String industry;
    private String positionType;  //full-time
    private String degree;
    private String major;
    private String experience;
    private String salary;
    private List<String> skills;
    private String requirements;
    private String responsibilities;
    private String benefits;
    private CompanyInfo companyInfo;


    @Override
    public String subTitle1() {
        return industry;
    }

    @Override
    public String subTitle2() {
        return jobTitle;
    }

    @Override
    public String subTitle3() {
        return positionType;
    }

    @Override
    public String subTitle4() {
        return experience;
    }

    @Override
    public TableType tableType() {
        return TableType.Jobs;
    }

    @Override
    public List<PostAttribute> attributeValueList() {
        List<PostAttribute> list = new ArrayList<>();
        list.add(new PostAttribute("职位", jobTitle));
        list.add(new PostAttribute("行业", industry));
        list.add(new PostAttribute("类别", positionType));
        list.add(new PostAttribute("学历", degree));
        list.add(new PostAttribute("专业", major));
        list.add(new PostAttribute("经验", experience));
        list.add(new PostAttribute("薪水", salary));
        return list;
    }

    @DynamoDBAttribute(attributeName = JOB_TITLE)
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @DynamoDBAttribute(attributeName = INDUSTRY)
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @DynamoDBAttribute(attributeName = POSITION_TYPE)
    public String getPositionType() {
        return positionType;
    }
    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    @DynamoDBAttribute(attributeName = DEGREE)
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }

    @DynamoDBAttribute(attributeName = MAJOR)
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    @DynamoDBAttribute(attributeName = EXPERIENCE)
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }

    @DynamoDBAttribute(attributeName = SALARY)
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }

    @DynamoDBAttribute(attributeName = SKILLS)
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @DynamoDBAttribute(attributeName = REQUIREMENTS)
    public String getRequirements() {
        return requirements;
    }
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @DynamoDBAttribute(attributeName = RESPONSIBILITIES)
    public String getResponsibilities() {
        return responsibilities;
    }
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    @DynamoDBAttribute(attributeName = BENEFITS)
    public String getBenefits() {
        return benefits;
    }
    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    @DynamoDBAttribute(attributeName = COMPANY_INFO)
    @DynamoDBMarshalling(marshallerClass = Converters.CompanyInfoConverter.class)
    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }
    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }
}
