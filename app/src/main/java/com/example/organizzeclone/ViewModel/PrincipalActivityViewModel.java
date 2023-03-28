package com.example.organizzeclone.ViewModel;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.organizzeclone.R;
import com.example.organizzeclone.Ui.Activitys.Config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class PrincipalActivityViewModel extends ViewModel {
    private final FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    public void signOut(){
        autenticacao.signOut();
    }

    public void configuraCalendarView(MaterialCalendarView calendarView){
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro","Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });
    }



}
