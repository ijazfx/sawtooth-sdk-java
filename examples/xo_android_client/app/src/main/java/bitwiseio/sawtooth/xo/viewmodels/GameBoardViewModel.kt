package bitwiseio.sawtooth.xo.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import bitwiseio.sawtooth.xo.models.Game
import bitwiseio.sawtooth.xo.state.XoStateRepository

class GameBoardViewModel(url: String) : ViewModel() {
    private var stateRepository: XoStateRepository = XoStateRepository(url)
    var game: LiveData<Game> = Transformations.switchMap(stateRepository.gameFocus) { input ->
        var m = MutableLiveData<Game>()
        m.value = input
        m
    }

    init { }

    fun loadGame(name: String, url: String) {
        stateRepository.getGameState(name, url)
        game = stateRepository.gameFocus
    }
}
