package cz.dmn.rxinteractors

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.CompletableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseCompletableRxInteractor<A>(
    private val disposables: CompositeDisposable,
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    fun <O> execute(argument: A, disposableObserver: O) where O : Disposable, O : CompletableObserver {
        disposables.add(buildInteractorObservable(argument)
            .compose(applySchedulers())
            .subscribeWith(disposableObserver))
    }

    private fun applySchedulers() = CompletableTransformer {
        it
            .subscribeOn(subscribeScheduler)
            .unsubscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
    }

    fun unsubscribe() {
        disposables.clear()
    }

    protected abstract fun buildInteractorObservable(argument: A): Completable
}