package com.in28minutes.rest.webservices.restfulwebservices.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RepeatCharacterRegexRule;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.in28minutes.rest.webservices.restfulwebservices.config.annotation.ValidPassword;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, PasswordData> {

  @Override
  public boolean isValid(PasswordData value, ConstraintValidatorContext context) {

    PasswordValidator validator = new PasswordValidator(getCurrentPasswordRules());

    RuleResult result = validator.validate(value);

    if(result.isValid()) {
      return true;
    }

    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
        validator.getMessages(result)
          .stream()
          .collect(Collectors.joining(", "))
    ).addConstraintViolation();

    return false;
  }

  private List<Rule> getCurrentPasswordRules() {
    return Arrays.asList(
        // Tamanho mínimo e máximo da senha
        new LengthRule(8, 32),

        // Ao menos um caracter deve ser maiúsculo
        new UppercaseCharacterRule(1),

        // Ao menos um número deve estar presente
        new DigitCharacterRule(1),

        // Ao menos um caracter especial deve estar presente
        new SpecialCharacterRule(1),

        // Não pode existir uma sequência de 3 números subsequentes
        new NumericalSequenceRule(3, false),

        // Não pode existir uma sequência de 3 letras subsequentes
        new AlphabeticalSequenceRule(3, false),

        // Não pode existir uma sequência de 3 caracteres subsequentes -baseado em suas posições no teclado-
        new QwertySequenceRule(3, false),

        // Não pode repetir 3 caracteres iguais numa mesma sequência
        new RepeatCharacterRegexRule(3),

        // Não pode conter espaços em branco na senha
        new WhitespaceRule()
    );
  }
}
