package com.cydeo.entity;

import com.cydeo.entity.common.UserPrincipal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//I am getting the all information belongs to user who login in the system

        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

      if (authentication != null && authentication.getName().equals("anonymousUser")) {
          Object principal = authentication.getPrincipal();
          baseEntity.setInsertUserId(((UserPrincipal) principal) .getId());
          baseEntity.setLastUpdateUserId( ((UserPrincipal) principal) .getId());
      }
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
        if (authentication != null && authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal();
            baseEntity.setInsertUserId(((UserPrincipal) principal) .getId());
            baseEntity.setLastUpdateUserId( ((UserPrincipal) principal) .getId());
        }
    }

   //whenever we create or update something, we are keeping some information which is who did it, what time did it?

}
