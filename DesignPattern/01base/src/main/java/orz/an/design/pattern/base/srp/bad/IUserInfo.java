package orz.an.design.pattern.base.srp.bad;

public interface IUserInfo {

    public void setId(String id);

    public String getId();

    public void setName(String name);

    public String getName();

    public void setPassWord(String passWord);

    public String getPassWord();

    public boolean changPassWord(String oldPassWord, String newPassWord);

    public boolean deleteUser(IUserInfo user);

    public boolean addUser(IUserInfo user);

    public boolean addOrg(Object object);

    public boolean addRole(Object object);
}
