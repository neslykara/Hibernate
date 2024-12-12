package com.tpe.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch04
{
    public static void main(String[] args) {
        Configuration config=new Configuration().configure()//hibernate.cfg.xml
                .addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);

        SessionFactory sessionFactory= config.buildSessionFactory();
        Session session=sessionFactory.openSession();

        //id : 1001 olan öğrenciyi getirelim
        Student04 student = session.get(Student04.class,1001);
        System.out.println(student);

        System.out.println("-------------------------");
        //id : 1001 olan öğrencinin günlüğünü getirin
        System.out.println(student.getDiary());

        System.out.println("-------------------------id-88");
        //id : 88 olan günlüğü getirelim
        Diary04 diary = session.get(Diary04.class,88);
        System.out.println(diary);


        //id : 88 olan günlüğün sahibinin bilgilerini getiriniz.
        System.out.println(diary.getStudent());


        System.out.println("------------------id-1003");
        //id : 1003 olan öğrencinin günlüğünü getiriniz
        Student04 student2 = session.get(Student04.class,1003);
        System.out.println(student2);
        System.out.println(student2.getDiary());//null

        //task: Bütün öğrenciler ve varsa bu öğrencilerin günlüklerini getirelim(öğrenci ismi ve günlük ismi)
        String hqlQuery ="SELECT s.name,d.name FROM Student04 s Left JOIN Diary04 d ON s.id=d.student.id";
        List<Object[]> resultList = session.createQuery(hqlQuery).getResultList();
        for (Object[] s: resultList){
            System.out.println(Arrays.toString(s));
        }

    }
}
