package com.foobar;

import com.foobar.foo.domain.User;
import com.foobar.foo.repo.UserRepository;
import com.foobar.mysql.domain.MyFoo;
import com.foobar.mysql.repo.MyFooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foobar.bar.domain.Bar;
import com.foobar.bar.repo.BarRepository;
import com.foobar.foo.domain.Foo;
import com.foobar.foo.repo.FooRepository;

@RestController
public class FooBarController {

  private final FooRepository fooRepo;
  private final BarRepository barRepo;
  private final MyFooRepository myFooRepository;
  private final UserRepository userRepository;

  @Autowired
  FooBarController(FooRepository fooRepo, BarRepository barRepo, MyFooRepository myFooRepository, UserRepository userRepository) {
    this.fooRepo = fooRepo;
    this.barRepo = barRepo;
    this.myFooRepository = myFooRepository;
//    this.myFooRepository = myFooRepository;
    this.userRepository = userRepository;
  }

  @RequestMapping("/foobar/{id}")
  public String fooBar(@PathVariable("id") Long id) {
    Foo foo = fooRepo.findById(id);
    Bar bar = barRepo.findById(id);
    MyFoo myFoo = myFooRepository.findOne(id);
    User user = userRepository.findOne(id);


    return foo.getFoo() + " " + bar.getBar() + " "+ user.getName() + " " + myFoo.getFoo()  + "!";
  }

}
