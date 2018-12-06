package thiengo.com.br.thiengosflix

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity :
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )
        setSupportActionBar( toolbar )

        fab.setOnClickListener( this )

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener( toggle )
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener( this )

        callFavoritesFragment()

        /*
         * Certificando de que desde a abertura do app o item
         * de menu gaveta selecionado (Favoritos) estará com
         * a formatação correta.
         * */
        setBoldNavigationViewItemSelected( R.id.nav_favorites )
    }

    override fun onBackPressed() {
        if( drawer_layout.isDrawerOpen( GravityCompat.START ) ){
            drawer_layout.closeDrawer( GravityCompat.START )
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu( menu: Menu ): Boolean {
        menuInflater.inflate( R.menu.favorites, menu )
        return true
    }

    override fun onNavigationItemSelected( item: MenuItem ): Boolean {
        when( item.itemId ){
            R.id.nav_favorites -> {
                callFavoritesFragment()
            }
        }

        drawer_layout.closeDrawer( GravityCompat.START )

        /*
         * Caso você queira reaproveitar o código com alguns
         * outros itens de menu passando pela mesma formatação
         * de texto, então descomente a linha abaixo.
         * */
        /*setBoldNavigationViewItemSelected( item.itemId ) */

        /*
         * Retornar false para manter sempre a tela de favoritos
         * do aplicativo de exemplo.
         * */
        return false
    }

    private fun callFavoritesFragment(){
        var fragment = supportFragmentManager.findFragmentByTag( FavoritesFragment.KEY )

        if( fragment == null ){
            fragment = FavoritesFragment()

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace( R.id.fl_frag_container, fragment, FavoritesFragment.KEY )
            transaction.commit()
        }
    }

    /*
     * Método necessário para colocar o item de menu gaveta
     * selecionado com uma formatação bold, negrito.
     * */
    private fun setBoldNavigationViewItemSelected( selectedItemId: Int ){
        val menu = nav_view.menu

        for( i in 0..(menu.size() - 1) ){
            val item = menu.getItem( i )

            if( item.itemId == selectedItemId ){
                /*
                 * Se for o item selecionado, coloque ele com
                 * negrito, utilizando SpannableString.
                 * */

                val textItem = SpannableString( item.title )

                textItem.setSpan(
                    StyleSpan( Typeface.BOLD ),
                    0,
                    textItem.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE )

                item.title = textItem
            }
            else{
                /*
                 * Basta um toString() para remover a formatação
                 * Spannable da String.
                 * */
                item.title = item.title.toString()
            }
        }
    }

    /*
     * Método responsável por conter o código de atualização
     * de título da atividade. Seria invocado em todos os
     * fragmentos relacionados aos itens de menu gaveta.
     * */
    fun updateActivityTitle( title: String ){
        toolbar.title = title
    }

    override fun onClick( v: View ){
        val fragment = supportFragmentManager.findFragmentByTag( FavoritesFragment.KEY )

        if( fragment is FavoritesFragment ){
            fragment.removeItemsSelected()
        }
    }
}
