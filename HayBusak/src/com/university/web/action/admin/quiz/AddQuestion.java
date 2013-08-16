package com.university.web.action.admin.quiz;

import com.university.data_access.service.quiz.IQuestionManager;
import com.university.model.account.GroupSubject;
import com.university.model.account.lcp.AcademicDegreeCourse;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.general.lcp.Language;
import com.university.model.quiz.Answer;
import com.university.model.quiz.Question;
import com.university.model.quiz.lcp.Type;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 13.08.13
 * Time: 1:16
 * To change this template use File | Settings | File Templates.
 */
public class AddQuestion extends ShellAction {

    private IQuestionManager questionManager;

    private String value;
    private String description;
    private Date creationDate;
    private List<Integer> groupSubjectIdes;
    private Type type;
    // one to many
    private List<String> answersValue;
    private List<Boolean> answersIsCorrect;

    public String preExecute() {
        return SUCCESS;
    }

    @Override
    public String execute() {
        Question question = new Question();
        List<Answer> answers = new ArrayList<Answer>();
        GroupSubject groupSubject;
        return SUCCESS;
    }

    public Type[] getTypes() {
        return Type.values();
    }
    public AcademicDegreeCourse[] getCourses() {
        return AcademicDegreeCourse.values();
    }

    public List<AcademicDegreeInfo> getDegreeInfos() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return null;
        }
        return AcademicDegreeInfo.listValueOfLanguage(language);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setGroupSubjectIdes(List<Integer> groupSubjectIdes) {
        this.groupSubjectIdes = groupSubjectIdes;
    }

    public void setType(int typeId) {
        this.type = Type.valueOf(typeId);
    }

    public void setAnswersValue(List<String> answersValue) {
        this.answersValue = answersValue;
    }

    public void setAnswersIsCorrect(List<Boolean> answersIsCorrect) {
        this.answersIsCorrect = answersIsCorrect;
    }

    public void setQuestionManager(IQuestionManager questionManager) {
        this.questionManager = questionManager;
    }
}
