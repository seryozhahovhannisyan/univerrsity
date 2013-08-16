package com.university.web.action.admin.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.data_access.service.account.IGroupSubjectManager;
import com.university.model.account.GroupSubject;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class AddSubjectsToGroups extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddSubjectsToGroups.class.getSimpleName());

   // private ICourseGroupManager groupManager;
    private IGroupSubjectManager groupSubjectManager;

    /*public void setGroupManager(ICourseGroupManager groupManager) {
        this.groupManager = groupManager;
    }*/

    public void setGroupSubjectManager(IGroupSubjectManager groupSubjectManager) {
        this.groupSubjectManager = groupSubjectManager;
    }

    private List<Integer> groupsId;
    private List<Integer> subjectsId;

    @Override
    public String execute() {
        try {
            // the more fast
            //groupManager.addSubjects(groupsId, subjectsId);

            List<GroupSubject> groupSubjects = new ArrayList<GroupSubject>();

            for (int g : groupsId) {
                for (int s : subjectsId) {

                    GroupSubject groupSubject = new GroupSubject();

                    groupSubject.setGroupId(g);
                    groupSubject.setSubjectId(s);

                    groupSubjects.add(groupSubject);
                }
            }
            groupSubjectManager.add(groupSubjects);
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to load subject to group", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.invalid.data"));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to load subject to group as occurred Duplicate Data ", e.getMessage()));
            return INPUT;
        }

    }

    @Override
    public void validate() {
        if (groupsId == null || groupsId.size() == 0) {
            logger.error(String.format("the field groupsIds are required"));
            addFieldError("groupsId", getText("errors.required"));
        }

        if (subjectsId == null || subjectsId.size() == 0) {
            logger.error(String.format("the field subjectsId are required"));
            addFieldError("groupsId", getText("errors.required"));
        }
    }

    public void setGroupsId(List<Integer> groupsId) {
        this.groupsId = groupsId;
    }

    public void setSubjectsId(List<Integer> subjectsId) {
        this.subjectsId = subjectsId;
    }
}
