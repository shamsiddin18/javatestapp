package com.simpleform.subject;

import com.simpleform.subject.model.Subject;
import com.simpleform.subject.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service

public class SubjectService {

    private SubjectRepository repository;

    public SubjectService (SubjectRepository repository) {
         this.repository=repository;
    }
    public Subject creatSubject( String title){
        if( title==null){
            return null;
        }

        if(repository.findFirstByTitle(title).isPresent()){
            //System.out.println("Dublicate subject");
            return  null;
        }

        Subject subject = new Subject();
       // subject.setId(id);
        subject.setTitle(title);

        return repository.save(subject);

    }

    public Subject authenticateSubject( String title){
        return repository.findFirstByTitle(title).orElse(null);

    }


}
