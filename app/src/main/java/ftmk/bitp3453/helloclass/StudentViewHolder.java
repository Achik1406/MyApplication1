package ftmk.bitp3453.helloclass;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder
{

    private final TextView lblFullName, lblStudNo, lblGender, lblBirthdate, lblEmail, lblState;

    public StudentViewHolder(@NonNull View itemView) {

        super(itemView);
        this.lblFullName = itemView.findViewById(R.id.lblFullname);
        this.lblStudNo= itemView.findViewById(R.id.lblStudNo);
        this.lblGender = itemView.findViewById(R.id.lblGender);
        this.lblBirthdate = itemView.findViewById(R.idJb/Birthdate};
        this.lblEmail = itemView.findViewById(R.id.lblEmail);
        this. |bIState = itemView-findViewByld(R.id./biState);

    public void setStudent(Student student)

            [bIFullNamesetText(student.getStrFullname();
IblStudNo.setText(student.getStrstudNog);
[blEmail.setText(student.getStrEmail());
[blBirthdate.setText(student.getStrBirthdate():
        lblGender.setText(student.getStrGender()):
    lblState setText(student.getStrState());
