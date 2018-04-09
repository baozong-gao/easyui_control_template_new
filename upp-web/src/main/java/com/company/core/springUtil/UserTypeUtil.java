package com.company.core.springUtil;

import com.company.core.constant.SystemConstant;
import com.company.core.entity.SysUsrDAO;
import com.company.core.Enum.FiledType;
import com.company.core.Enum.UserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @Author: gaobaozong
 * @Description: 跟据用户类型，向bo中赋值
 * @Date: Created in 2018/3/27 - 13:51
 * @Version: V1.0
 */
@Slf4j
public class UserTypeUtil {


    public static void initTypeId(HttpServletRequest request, Object bo) {
        if (bo == null) {
            return;
        }
        try {
            SysUsrDAO user = (SysUsrDAO) request.getSession().getAttribute(SystemConstant.USER_SESSION_KEY);
            if (SystemConstant.ROOT.equals(user.getName())) {
                return ;
            }

            Object[] v1 = new Object[]{user.getInstId(), "01".equals(user.getUserType()) ? user.getUserTypeId() : null, "00".equals(user.getUserType()) ? user.getUserTypeId() : null};

            Field[] fields = bo.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(FiledType.class)) {
                    UserTypeEnum type = field.getAnnotation(FiledType.class).value();
                    switch (type) {
                        case INST:
                            PropertyUtils.setProperty(bo, field.getName(), v1[0]);
                            break;
                        case MER:
                            if(PropertyUtils.getProperty(bo, field.getName()) == null)
                            PropertyUtils.setProperty(bo, field.getName(), v1[1]);
                            break;
                        case AGE:
                            if(!"99".equals(user.getUserType()))
                            PropertyUtils.setProperty(bo, field.getName(), v1[2]);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            log.error("init user type error " , e);
        }
    }

}
