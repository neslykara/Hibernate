package com.hb03.uni_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.spi.Configurable;
public class RunnerFetch03
{
    public static void main(String[] args)
    {
        Configuration config = new Configuration().configure().addAnnotatedClass(Student03.class)//hibernate.cfg.xml
                .addAnnotatedClass(Diary.class);
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //id'si 1001 olan öğrenciyi görüntüleyelim

        Student03 student = session.get(Student03.class,1001);
        System.out.println(student);

        //id 11 olan günlüğü getirelim
        Diary diary = session.get(Diary.class,11);
        System.out.println(diary);

        System.out.println("************************");
        System.out.println(student);
        System.out.println("************************");
        System.out.println(diary);
        System.out.println("************************");



        //günlük kime ait?
        System.out.println(diary.getStudent());//günlüğe sahip öğrencinin bilgilerini getirir.




        //id si 1002 olan öğrenciye ait günlüğü bulmak istersek ne yapacağız?
        Student03 student2 = session.get(Student03.class,1002);
        //student2.getDiary();//--> ilişkiyi diary üzerinden kurduk java kodlari buna ulasamıyoruz o zmn ne yapıcaz DB'den cekebiliriz

        String diaryName = (String) session.createSQLQuery("Select name from t_diary where std_id=1002 ").uniqueResult();
        System.out.println("Name : "+diaryName);

         /*
        PROBLEM : sorgu yazmadan diaryden studenta ulşabiliyorkey ,studentta diarye ulaşamıyorum
        ulaşmak istersem?

        //uni_directional(Tek Yönlü) : Diary  -->Student
        //bi_directional(Çift Yönlü) : Diary <-->Student(veritabanında birşey değişmez) :)
         */

    }
}
