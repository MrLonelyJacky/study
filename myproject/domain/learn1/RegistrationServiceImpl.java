package domain.learn1;

public class RegistrationServiceImpl implements RegistrationService {
    public User register(String name, String phone)
            throws ValidationException {
        // 参数校验
        if (name == null || name.length() == 0) {
            throw new ValidationException("name");
        }
        if (phone == null || !isValidPhoneNumber(phone)) {
            throw new ValidationException("phone");
        }
// 最后创建用户，落盘，然后返回
        User user = new User();
        user.name = name;
        user.phone = phone;


        // 获取手机号归属地编号和运营商编号 然后通过编号找到区域内的SalesRep
       /* String areaCode = getAreaCode(phone);
        String operatorCode = getOperatorCode(phone);
        SalesRep rep = salesRepRepo.findRep(areaCode, operatorCode);

        // 最后创建用户，落盘，然后返回
        User user = new User();
        user.name = name;
        user.phone = phone;

        if (rep != null) {
            user.repId = rep.repId;
        }

        return userRepo.save(user);*/
        return user;
    }

    private boolean isValidPhoneNumber(String phone) {
        String pattern = "^0[1-9]{2,3}-?\\d{8}$";
        return phone.matches(pattern);
    }

    private String getAreaCode(String phone) {
        //...
        return "";
    }

    private String getOperatorCode(String phone) {
        //...
        return "";
    }
}
