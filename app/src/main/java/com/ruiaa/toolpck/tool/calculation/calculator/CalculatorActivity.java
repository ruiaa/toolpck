package com.ruiaa.toolpck.tool.calculation.calculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityCalculatorBinding;
import com.ruiaa.toolpck.tool.measure.timecount.TimeCountUtil;
import com.ruiaa.toolpck.util.LogUtil;
import com.ruiaa.toolpck.util.ResUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorActivity extends ToolbarActivity {

    private ActivityCalculatorBinding binding;
    private TimeCountUtil timeCountUtil;

    private StringBuilder stringBuilder;

    private String multiplyChar;
    private String divideChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);
        ButterKnife.bind(this);
        timeCountUtil = new TimeCountUtil(this, false);

        multiplyChar=ResUtil.getString(R.string.calculator_multiply);
        divideChar=ResUtil.getString(R.string.calculator_divide);
        stringBuilder = new StringBuilder();


        setToolbar();
    }

    private void setToolbar() {
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.calculator);
    }

    @OnClick({R.id.calculator_clear, R.id.calculator_delete_last, R.id.calculator_divide, R.id.calculator_multiply,
            R.id.calculator_seven, R.id.calculator_eight, R.id.calculator_nine, R.id.calculator_subtract,
            R.id.calculator_four, R.id.calculator_five, R.id.calculator_six, R.id.calculator_add,
            R.id.calculator_one, R.id.calculator_two, R.id.calculator_three, R.id.calculator_equal,
            R.id.calculator_bracket, R.id.calculator_0, R.id.calculator_point})
    public void onInputClick(View view) {
        switch (view.getId()) {
            case R.id.calculator_clear: {
                clear();
                break;
            }
            case R.id.calculator_delete_last: {
                deleteLast();
                break;
            }
            case R.id.calculator_divide: {
                addAddSubTractMultiplyDivide(divideChar);
                break;
            }
            case R.id.calculator_multiply: {
                addAddSubTractMultiplyDivide(multiplyChar);
                break;
            }
            case R.id.calculator_seven: {
                addNumber(7);
                break;
            }
            case R.id.calculator_eight: {
                addNumber(8);
                break;
            }
            case R.id.calculator_nine: {
                addNumber(9);
                break;
            }
            case R.id.calculator_subtract: {
                addAddSubTractMultiplyDivide("-");
                break;
            }
            case R.id.calculator_four: {
                addNumber(4);
                break;
            }
            case R.id.calculator_five: {
                addNumber(5);
                break;
            }
            case R.id.calculator_six: {
                addNumber(6);
                break;
            }
            case R.id.calculator_add: {
                addAddSubTractMultiplyDivide("+");
                break;
            }
            case R.id.calculator_one: {
                addNumber(1);
                break;
            }
            case R.id.calculator_two: {
                addNumber(2);
                break;
            }
            case R.id.calculator_three: {
                addNumber(3);
                break;
            }
            case R.id.calculator_equal: {
                calculate();
                return;
            }
            case R.id.calculator_bracket: {
                addBracket();
                break;
            }
            case R.id.calculator_0: {
                addNumber(0);
                break;
            }
            case R.id.calculator_point: {
                addPoint();
                break;
            }
        }
        updateInput();
    }

    @OnClick({R.id.calculator_record_1, R.id.calculator_record_2, R.id.calculator_record_3})
    public void onRecordClick(View view) {
        switch (view.getId()) {
            case R.id.calculator_record_1: {
                setResultFromRecord(binding.calculatorRecord1.getText());
                break;
            }
            case R.id.calculator_record_2: {
                setResultFromRecord(binding.calculatorRecord2.getText());
                break;
            }
            case R.id.calculator_record_3: {
                setResultFromRecord(binding.calculatorRecord3.getText());
                break;
            }
        }
    }

    private void addNumber(int num) {
        if (stringBuilder.length() == 0) {
            stringBuilder.append(num);
        } else if (stringBuilder.charAt(stringBuilder.length() - 1) == ')') {
            stringBuilder.append("*");
            stringBuilder.append(num);
        } else {
            stringBuilder.append(num);
        }
    }

    private void addPoint() {
        if (stringBuilder.length() == 0) {
            stringBuilder.append("0.");
        } else if (CalculateUtil.isNumber(stringBuilder.charAt(stringBuilder.length() - 1))) {
            stringBuilder.append(".");
        } else if (stringBuilder.charAt(stringBuilder.length() - 1) == ')') {
            stringBuilder.append("*0.");
        } else {
            stringBuilder.append("0.");
        }
    }

    private void addAddSubTractMultiplyDivide(String s) {
        if (stringBuilder.length() == 0) {
            if ("-".equals(s)) {
                stringBuilder.append("-");
            }
        } else if (stringBuilder.length() == 1 && stringBuilder.charAt(0) == '-') {
            if (!"-".equals(s)) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        } else if (CalculateUtil.isNumber(stringBuilder.charAt(stringBuilder.length() - 1))) {
            stringBuilder.append(s);
        } else if (stringBuilder.charAt(stringBuilder.length() - 1) == ')') {
            stringBuilder.append(s);
        } else if (stringBuilder.charAt(stringBuilder.length() - 1) == '(') {
            if ("-".equals(s)) {
                stringBuilder.append("-");
            }
        } else if (CalculateUtil.isOperator_add_subtract_multiply_divide(stringBuilder.charAt(stringBuilder.length() - 1))) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(s);
        }
    }

    private void addBracket() {
        if (stringBuilder.length() == 0) {
            stringBuilder.append("(");
        } else if (CalculateUtil.isNumber(stringBuilder.charAt(stringBuilder.length() - 1)) || stringBuilder.charAt(stringBuilder.length() - 1) == ')') {
            if (CalculateUtil.getBracketLeftCount(stringBuilder.toString()) > CalculateUtil.getBracketRightCount(stringBuilder.toString())) {
                stringBuilder.append(")");
            } else {
                stringBuilder.append("*(");
            }
        } else if (stringBuilder.charAt(stringBuilder.length() - 1) == '(') {
            stringBuilder.append("(");
        } else if (CalculateUtil.isOperator_add_subtract_multiply_divide(stringBuilder.charAt(stringBuilder.length() - 1))) {
            stringBuilder.append("(");
        }
    }

    private void clear() {
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(0, stringBuilder.length());
        }

    }

    private void deleteLast() {
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }


    private void calculate() {
        if (stringBuilder.length() > 0) {
            String result;
            try {
                double d = CalculateUtil.calculate(stringBuilder.toString().replaceAll(multiplyChar,"*").replaceAll(divideChar,"/"));
                clear();
                stringBuilder.append(formatResult(d));
                binding.calculatorInputResult.setText(stringBuilder.toString());
                setRecord(stringBuilder.toString());
            } catch (Exception e) {
                LogUtil.e("calculate####", e);
                clear();
                binding.calculatorInputResult.setText(R.string.error);
                clear();
            }
        }
    }

    private void setRecord(String newResult) {
        try{
            Double d=Double.parseDouble(newResult);
            if (!d.isInfinite()&&!d.isNaN()){
                binding.calculatorRecord1.setText(binding.calculatorRecord2.getText());
                binding.calculatorRecord2.setText(binding.calculatorRecord3.getText());
                binding.calculatorRecord3.setText(newResult);
            }
        }catch (NumberFormatException e){
            LogUtil.w("setRecord####",e);
        }
    }

    private void setResultFromRecord(@Nullable CharSequence record){
        if (record==null) return;
        clear();
        stringBuilder.append(record);
        binding.calculatorInputResult.setText(stringBuilder.toString());
    }

    private void updateInput(){
        if (stringBuilder.length()==0) {
            binding.calculatorInputResult.setText("");
        }else {
            binding.calculatorInputResult.setText(stringBuilder.toString());
            if (binding.calculatorInputResult.getText().length() > 0) {
                binding.calculatorInputResult.setSelection(binding.calculatorInputResult.getText().length() - 1);
            }
        }
    }

    private String formatResult(double d) {
        /*String s = String.format("%.10f",d);
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;*/
        String s = String.valueOf(d);
        return s.replaceAll(".0$", "");
    }

}
