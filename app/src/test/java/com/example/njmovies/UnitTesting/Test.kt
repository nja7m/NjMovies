package com.example.njmovies.UnitTesting

import com.example.njmovies.util.Validation
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class Test {
	private lateinit var validator: Validation

	@Before
	fun setup(){
		validator = Validation()
	}
	@Test
	fun emailIsValidWithInvalidEmailThenReturnFalseValue(){
		val validation = validator.emailIsValid("test@test-com")
		Assert.assertEquals(false,validation)
	}

	@Test
	fun emailIsValidWithValidEmailThenReturnTrueValue(){
		val validation = validator.emailIsValid("test@test.com")
		Assert.assertEquals(true,validation)
	}
	@Test
	fun passwordIsValidWithInvalidPasswordThenReturnFalseValue(){
		val validation = validator.passwordIsValid("677")
		Assert.assertEquals(false,validation)
	}
	@Test
	fun passwordIsValidWithValidPasswordThenReturnTrueValue(){
		val validation = validator.passwordIsValid("Nj@14101414")
		Assert.assertEquals(true,validation)
	}

}