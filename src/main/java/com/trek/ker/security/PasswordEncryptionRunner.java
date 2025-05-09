// VAJADZĪGS TIKAI PALAIŽOT PIRMO REIZI
// package com.trek.ker.security;
//
//import com.trek.ker.entity.User;
//import com.trek.ker.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import jakarta.transaction.Transactional;
//
//@Component
//public class PasswordEncryptionRunner implements CommandLineRunner {
//
//    private final UserRepository userRepo;
//    private final PasswordEncoder encoder;
//
//    public PasswordEncryptionRunner(UserRepository userRepo, PasswordEncoder encoder) {
//        this.userRepo = userRepo;
//        this.encoder = encoder;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        for (User user : userRepo.findAll()) {
//            String raw = user.getPassword();
//            if (!raw.startsWith("$2a$")) { // only encode if not already BCrypted
//                String hashed = encoder.encode(raw);
//                user.setPassword(hashed);
//                userRepo.save(user);
//            }
//        }
//        System.out.println("✓ All existing user passwords have been encrypted.");
//    }
//}
