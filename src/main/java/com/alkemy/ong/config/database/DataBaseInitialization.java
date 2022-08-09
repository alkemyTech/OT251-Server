package com.alkemy.ong.config.database;

import com.alkemy.ong.models.Activity;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.models.Role;
import com.alkemy.ong.repositories.ActivityRepository;
import com.alkemy.ong.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBaseInitialization implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Override
    public void run(String...args) throws Exception {

        if(roleRepo.findByName("USER") == null){
            Role user = new Role();
            user.setName("USER");
            user.setDescription("User default.");
            roleRepo.save(user);
        }
        if(roleRepo.findByName("ADMIN") == null){
            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setDescription("User with administrator permissions.");
            roleRepo.save(admin);
        }

        List<Activity> activities = new ArrayList<>();
        Activity activity1 = new Activity();
        activity1.setName("Apoyo Escolar para el nivel Primario");
        activity1.setContent(   "El espacio de apoyo escolar es el corazón del área educativa. Se realizan los\n" +
                                "talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el\n" +
                                "contraturno, Los sábados también se realiza el taller para niños y niñas que\n" +
                                "asisten a la escuela doble turno. Tenemos un espacio especial para los de\n" +
                                "1er grado 2 veces por semana ya que ellos necesitan atención especial!\n" +
                                "Actualmente se encuentran inscriptos a este programa 150 niños y niñas de\n" +
                                "6 a 15 años. Este taller está pensado para ayudar a los alumnos con el\n" +
                                "material que traen de la escuela, también tenemos una docente que les da\n" +
                                "clases de lengua y matemática con una planificación propia que armamos\n" +
                                "en Manos para nivelar a los niños y que vayan con más herramientas a la\n" +
                                "escuela.\n");
        activity1.setImage("SET_URL_IMG"); //change url image
        activities.add(activity1);
        Activity activity2 = new Activity();
        activity2.setName("Apoyo Escolar Nivel Secundaria");
        activity2.setContent(   "Del mismo modo que en primaria, este taller es el corazón del área\n" +
                                "secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a\n" +
                                "18 horas en el contraturno. Actualmente se encuentran inscriptos en el taller\n" +
                                "50 adolescentes entre 13 y 20 años. Aquí los jóvenes se presentan con el\n" +
                                "material que traen del colegio y una docente de la institución y un grupo de\n" +
                                "voluntarios los recibe para ayudarlos a estudiar o hacer la tarea. Este\n" +
                                "espacio también es utilizado por los jóvenes como un punto de encuentro y\n" +
                                "relación entre ellos y la institución");
        activity2.setImage("SET_URL_IMG"); //change url image
        activities.add(activity2);
        Activity activity3 = new Activity();
        activity3.setName("Tutorías");
        activity3.setContent(   "Es un programa destinado a jóvenes a partir del tercer año de secundaria,\n" +
                                "cuyo objetivo es garantizar su permanencia en la escuela y construir un\n" +
                                "proyecto de vida que da sentido al colegio. El objetivo de esta propuesta es\n" +
                                "lograr la integración escolar de niños y jóvenes del barrio promoviendo el\n" +
                                "soporte socioeducativo y emocional apropiado, desarrollando los recursos\n" +
                                "institucionales necesarios a través de la articulación de nuestras\n" +
                                "intervenciones con las escuelas que los alojan, con las familias de los\n" +
                                "alumnos y con las instancias municipales, provinciales y nacionales que\n" +
                                "correspondan. El programa contempla:\n" +
                                "● Encuentro semanal con tutores (Individuales y grupales)\n" +
                                "● Actividad proyecto (los participantes del programa deben pensar una\n" +
                                "actividad relacionada a lo que quieren hacer una vez terminada la\n" +
                                "escuela y su tutor los acompaña en el proceso)\n" +
                                "● Ayudantías (los que comienzan el 2do años del programa deben\n" +
                                "elegir una de las actividades que se realizan en la institución para");
        activity3.setImage("SET_URL_IMG"); //change url image
        activities.add(activity3);
        
        for (Activity activity : activities) {
            if(activityRepo.findByName(activity.getName())==null){
                activityRepo.save(activity);
            }

        }

    }
}
