package codcat.magnitrailstation.base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}