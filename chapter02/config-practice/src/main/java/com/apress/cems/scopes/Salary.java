/*
Freeware License, some rights reserved

Copyright (c) 2019 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.cems.scopes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
// TODO 14. Redefine this bean to configure JDK interface based proxying. Add classes or interfaces necessary.
// TODO. 15 Create a specialized version of the @Scope annotation you used on this bean to solve requirement 14.
@Description("Salary for an employee might change, so this is a suitable example for a prototype scoped bean")
@Component
@SalariesScope(proxyMode = ScopedProxyMode.DEFAULT)
@Slf4j
public class Salary implements ISalary {

    private Integer amount;

    public Salary() {
        log.info(" -> Creating new Salary bean");
        Random rand = new Random();
        this.amount = rand.nextInt(10_000) +  50_000;
    }

    public Integer getAmount() {
        return amount;
    }
}
