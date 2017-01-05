/**
 * generated by Xtext
 */
package no.hal.jex.jextest.validation;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.Iterator;
import no.hal.jex.jextest.jexTest.JexTestPackage;
import no.hal.jex.jextest.jexTest.JexTestSequence;
import no.hal.jex.jextest.jexTest.JvmOperationRef;
import no.hal.jex.jextest.jexTest.Method;
import no.hal.jex.jextest.jexTest.Parameter;
import no.hal.jex.jextest.jexTest.ParameterList;
import no.hal.jex.jextest.jexTest.StateFunction;
import no.hal.jex.jextest.jvmmodel.Util;
import no.hal.jex.jextest.validation.AbstractJexTestValidator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * Custom validation rules.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
@SuppressWarnings("all")
public class JexTestValidator extends AbstractJexTestValidator {
  @Override
  public void checkInnerExpressions(final XExpression expr) {
  }
  
  public boolean checkVarargIsLast(final Collection<Parameter> parameters, final EStructuralFeature feature) {
    boolean _xblockexpression = false;
    {
      final Iterator<Parameter> iterator = parameters.iterator();
      while (iterator.hasNext()) {
        boolean _and = false;
        Parameter _next = iterator.next();
        boolean _isVararg = _next.isVararg();
        if (!_isVararg) {
          _and = false;
        } else {
          boolean _hasNext = iterator.hasNext();
          _and = _hasNext;
        }
        if (_and) {
          this.error("Only the last paramter can be a vararg", feature);
        }
      }
      _xblockexpression = true;
    }
    return _xblockexpression;
  }
  
  @Check
  public boolean checkVarargIsLast(final StateFunction method) {
    EList<Parameter> _parameters = method.getParameters();
    return this.checkVarargIsLast(_parameters, JexTestPackage.Literals.STATE_FUNCTION__PARAMETERS);
  }
  
  @Check
  public boolean checkVarargIsLast(final Method method) {
    ParameterList _parameters = method.getParameters();
    EList<Parameter> _parameters_1 = _parameters.getParameters();
    return this.checkVarargIsLast(_parameters_1, JexTestPackage.Literals.METHOD__PARAMETERS);
  }
  
  @Inject
  @Extension
  private Util _util;
  
  @Check
  public boolean jvmOperationRefExists(final JexTestSequence sequence) {
    boolean _xblockexpression = false;
    {
      int index = 0;
      EList<JvmOperationRef> _tested = sequence.getTested();
      for (final JvmOperationRef opRef : _tested) {
        {
          JvmOperation _resolveOperatorRef = this._util.resolveOperatorRef(opRef);
          boolean _equals = Objects.equal(_resolveOperatorRef, null);
          if (_equals) {
            this.error("Cannot resolve method/constructor reference", JexTestPackage.Literals.JEX_TEST_SEQUENCE__TESTED, index);
          }
          index++;
        }
      }
      _xblockexpression = true;
    }
    return _xblockexpression;
  }
}
