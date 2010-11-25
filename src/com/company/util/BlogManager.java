package com.company.util;

import org.hibernate.Session;

import java.util.*;

import com.company.models.Blog;

public class BlogManager {

    public static void main(String[] args) {
        BlogManager mgr = new BlogManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreBlog("My Blog", new Date());
            Blog b = mgr.find(args[1]);
            System.out.println(b);
        }

        HibernateUtil.getSessionFactory().close();
    }


    public Blog find(String id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Blog b = (Blog) session.get(Blog.class, Long.parseLong(id));
        session.getTransaction().commit();
        return b;
    }    

    
    public List list(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List l = session.createQuery("from Blog").list();
        session.getTransaction().commit();
        return l;
    }    

    private void createAndStoreBlog(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Blog theBlog = new Blog();
        theBlog.setTitle(title);
        theBlog.setCreatedAt(theDate);
        session.save(theBlog);
        session.getTransaction().commit();
    }

}