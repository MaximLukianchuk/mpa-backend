package com.itmo.mpa.service.impl.parsing

import com.itmo.mpa.service.PredicateService
import com.itmo.mpa.service.impl.parsing.model.Either
import com.itmo.mpa.service.impl.parsing.model.evaluate
import org.springframework.stereotype.Service

@Service
class PredicateServiceImpl(
        private val parser: Parser
) : PredicateService {

    override fun parsePredicate(predicate: String): (List<Either<Double, String>>) -> Boolean {
        val parsedExpression = parser.parse(predicate)
        return { parsedExpression.evaluate(it) }
    }
}
