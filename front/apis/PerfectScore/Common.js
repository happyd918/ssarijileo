const Common = class {
  _handlers = new Map();
  on(e, t, r = false) {
    this._handlers.has(e) || this._handlers.set(e, []),
      this._handlers.get(e).push({ handler: t, once: r });
  }
  emit(e, ...t) {
    if (!this._handlers.has(e)) return;
    let r = this._handlers.get(e);
    r.forEach(i => {
      i.handler(...t);
    });
    for (let i = r.length - 1; i >= 0; i--) r[i].once && r.splice(i, 1);
  }
};

export default Common;
