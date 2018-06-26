package com.innovati.felipehernandez.invenenvios.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.innovati.felipehernandez.invenenvios.fragments.BusquedaArticulosFragment;
import com.innovati.felipehernandez.invenenvios.fragments.BusquedaClienteFragment;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;

public class TabsAdapter extends FragmentStatePagerAdapter
{
    private int numberOfTab;

    public TabsAdapter(FragmentManager fm, int tabs)
    {
        super(fm);
        this.numberOfTab = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new BusquedaClienteFragment();
            case 1:
                return new BusquedaArticulosFragment();
            case 2:
                return new DatosPedidoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }
}
