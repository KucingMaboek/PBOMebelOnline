package PBOMebelOnline;

public interface CRUD {
    public String createQuery();
    public String updateQuery();
    public String deleteQuery();
    public void menu(boolean admin) throws Exception;
}
