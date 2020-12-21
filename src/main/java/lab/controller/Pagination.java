package lab.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class Pagination {
    public static void pagination(HttpServletRequest req, ArrayList arr, String attributeName, int page_size) {

        if (arr != null) {
            if (arr.size() > page_size) {

                int lastPage = (arr.size() / page_size) + 1;

                req.setAttribute("lastPage", lastPage);

                if (req.getParameter("page") == null || req.getParameter("page").equals("")) {
                    req.setAttribute(attributeName, arr.subList(0, page_size));
                    req.setAttribute("page", 1);
                } else {
                    int page = Integer.parseInt(req.getParameter("page"));
                    if (page == lastPage) {
                        req.setAttribute(attributeName, arr.subList((page - 1) * page_size, arr.size()));
                    } else
                        req.setAttribute(attributeName, arr.subList((page - 1) * page_size, page * page_size));

                    req.setAttribute("page", page);
                }
            } else
                req.setAttribute(attributeName, arr);
        } else
            req.setAttribute(attributeName, null);
    }
}
